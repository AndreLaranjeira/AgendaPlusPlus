package service

import model.Project
import model.NewProject
import model.TB_Project
import org.jetbrains.exposed.sql.*
import service.DatabaseFactory.dbQuery

class ProjectService {

    suspend fun getAllProjects(): List<Project> = dbQuery {
        TB_Project.selectAll().map { toProject(it) }
    }

    suspend fun getProject(id: Long): Project? = dbQuery {
        TB_Project.select {
            (TB_Project.id_project eq id)
        }.mapNotNull { toProject(it) }
                .singleOrNull()
    }

    suspend fun updateProject(updatedProject: NewProject): Project {
        val id = updatedProject.id_project
        return if (id == null) {
            addProject(updatedProject)
        } else {
            dbQuery {
                TB_Project.update({ TB_Project.id_project eq id }) {
                    it[project_title] = updatedProject.project_title
                    it[project_description] = updatedProject.project_description
                    it[is_active] = updatedProject.is_active
                    it[fk_group] = updatedProject.fk_group
                    it[fk_admin] = updatedProject.fk_admin
                }
            }
            getProject(id)!!
        }
    }

    suspend fun addProject(newProject: NewProject): Project {
        var key: Long? = 0
        dbQuery {
            key = TB_Project.insert({
                it[project_title] = newProject.project_title
                it[project_description] = newProject.project_description
                it[is_active] = newProject.is_active
                it[fk_group] = newProject.fk_group
                it[fk_admin] = newProject.fk_admin
            }) get TB_Project.id_project
        }
        return getProject(key!!)!!
    }

    suspend fun deleteProject(id: Long): Boolean = dbQuery {
        TB_Project.deleteWhere { TB_Project.id_project eq id } > 0
    }

    private fun toProject(row: ResultRow): Project =
            Project(
                    id_project = row[TB_Project.id_project],
                    project_title = row[TB_Project.project_title],
                    project_description = row[TB_Project.project_description],
                    is_active = row[TB_Project.is_active],
                    fk_group = row[TB_Project.fk_group],
                    fk_admin = row[TB_Project.fk_admin]
            )
}
