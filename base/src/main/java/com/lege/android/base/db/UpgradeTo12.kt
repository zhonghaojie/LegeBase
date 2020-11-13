package com.lege.launcher.db

import com.lege.android.base.string.DateFormatUtil
import com.lege.launcher.apapter.*

/**
 * Description:
 * Created by zhonghaojie on 2019-11-05.
 */
class UpgradeTo12 {
    fun transfer() {
        val scheduleUsers = DBHelper.getInstance().findAllScheduleUserData()
        val plans = DBHelper.getInstance().findPlanUserAllData()
        val alarms = DBHelper.getInstance().findAllAlarmUserData()
        val remind = DBHelper.getInstance().findReminderAllData()
        var task: TaskBean
        scheduleUsers.forEach { it ->
            task = schedule2TaskBean(it)
            DBHelper.getInstance().taskBeanDao.insertOrReplace(task)
        }
        plans.forEach {
            task = plan2taskBean(it)
            DBHelper.getInstance().taskBeanDao.insertOrReplace(task)
        }
//        alarms.forEach {
//            task = alarm2TaskBean(it)
//            DBHelper.getInstance().taskBeanDao.insert(task)
//        }
//        remind.forEach {
//            task = remind2TaskBean(it)
//            DBHelper.getInstance().taskBeanDao.insert(task)
//        }
    }

    fun schedule2TaskBean(scheduleUser: ScheduleUser): TaskBean {
        val task = TaskBean()
        task.alertTime = scheduleUser.alert
        task.advance = scheduleUser.alert.toInt()
        task.datetime = scheduleUser.startdate.trim() + " " + scheduleUser.starttime.trim()
        task.date = scheduleUser.startdate.trim()
        task.startTime = scheduleUser.startdate.trim() + " " + scheduleUser.starttime.trim()
        task.endTime = scheduleUser.enddate.trim()+" "+scheduleUser.endtime.trim()
        task.setIsRead(scheduleUser.readed == "yes")
        task.setRepeate(false)
        task.state = TASK_STATE_NORMAL
        task.repeateType = ""
        task.subject = scheduleUser.title
        task.type = TASK_SCHEDULE
        task.taskId = scheduleUser.scheduleid
        return task
    }

    fun plan2taskBean(planUser: PlanUser): TaskBean {
        val task = TaskBean()
        task.date = planUser.duedate.split(" ")[0]
        task.datetime = planUser.duedate
        task.taskId = planUser.taskid
        task.subject = planUser.subject
        if(DateFormatUtil.getSystemCurrent_Time2()>planUser.duedate){
            task.startTime =planUser.duedate
        }else{
            task.startTime = DateFormatUtil.getSystemCurrent_Time2()
        }
        task.endTime = planUser.duedate
        task.type = TASK_PLAN
        if (planUser.status == 0) {
            task.state = TASK_STATE_NORMAL
        } else if (planUser.status == 1) {
            task.state = TASK_STATE_FINISH
        }
        return task
    }

    fun alarm2TaskBean(alarmUser: AlarmUser): TaskBean {
        val task = TaskBean()
        task.subject = "闹钟"
        task.taskId = alarmUser.alarmid
        task.datetime = alarmUser.startdate.trim() + " " + alarmUser.time.trim()
        task.alarmRingType = alarmUser.ringingid.toInt()
        task.setRepeate(true)
        task.type = TASK_ALARM
        task.repeateType = alarmUser.repeat
        task.alarmIsStart = alarmUser.isStart
        task.state = TASK_STATE_NORMAL
        return task
    }

    fun remind2TaskBean(reminderUser: ReminderUser): TaskBean {
        val task = TaskBean()
        task.subject = reminderUser.remindersubject
        task.taskId = reminderUser.remindid
        task.setRepeate(true)
        task.type = TASK_REMIND
        task.repeateType = reminderUser.reminderrepeat
        task.remindInterval = reminderUser.reminderinterval.toInt()
        task.remindType = reminderUser.remindertype
        task.remindStartTime = reminderUser.reminderstarttime
        task.remindEndTime = reminderUser.reminderendtime
        task.state = TASK_STATE_NORMAL
        if (reminderUser.reminderrepeat == "7") {
            task.datetime = DateFormatUtil.getSystemCurrent_Time()
        }
        return task
    }
}