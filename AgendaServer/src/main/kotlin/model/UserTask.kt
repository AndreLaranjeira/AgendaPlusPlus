package model

import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime

object UserTasks : Table(){
    val id_task = integer("id_task").primaryKey().autoIncrement()
    val task_title = varchar("task_title", 50)
    val task_description = text("task_description")
    val task_limit = datetime("task_limit")
    val task_done = datetime("task_done").nullable()
}
/*CREATE TABLE IF NOT EXISTS `AgendaPlusPlus`.`User_Task` (
`id_task` BIGINT NOT NULL AUTO_INCREMENT,
`task_title` VARCHAR(50) NOT NULL,
`task_description` TEXT NOT NULL,
`task_limit` DATETIME NULL,
`task_done` DATETIME NULL,
`fk_user` BIGINT NOT NULL,
PRIMARY KEY (`id_task`),
INDEX `fk_user_idx` (`fk_user` ASC),
CONSTRAINT `fk_user_task`
FOREIGN KEY (`fk_user`)
REFERENCES `AgendaPlusPlus`.`User` (`id_user`)
ON DELETE NO ACTION
ON UPDATE NO ACTION)
ENGINE = InnoDB;*/

data class UserTask (
        val id_task : Int,
        val task_title : String,
        val task_description : String,
        val task_limit : DateTime,
        val task_done : DateTime?
)

data class NewUserTask(
        val id_task : Int?,
        val task_title : String,
        val task_description : String,
        val task_limit : DateTime,
        val task_done : DateTime?
)