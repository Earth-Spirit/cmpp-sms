package com.iwhalecloud.nmyd.cmppsms.schedule;

import com.iwhalecloud.nmyd.cmppsms.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*

1.首先我们在springboot的启动类中加入@EnableScheduling注解


@Scheduled注解参数
    （1）cron：cron表达式，指定任务在特定时间执行；
    （2）fixedDelay：表示上一次任务执行完成后多久再次执行，参数类型为long，单位ms；
    （3）fixedDelayString：与fixedDelay含义一样，只是参数类型变为String；
    （4）fixedRate：表示按一定的频率执行任务，参数类型为long，单位ms；
    （5）fixedRateString: 与fixedRate的含义一样，只是将参数类型变为String；
    （6）initialDelay：表示延迟多久再第一次执行任务，参数类型为long，单位ms；
    （7）initialDelayString：与initialDelay的含义一样，只是将参数类型变为String；
    （8）zone：时区，默认为当前时区，一般没有用到。


    cron表达式
 Cron表达式是一个字符串，是由空格隔开的6或7个域组成，每一个域对应一个含义（秒 分 时 每月第几天 月 星期 年）其中年是可选字段。
 但是，此处敲一下黑板，spring的schedule值支持6个域的表达式，也就是不能设定年，如果超过六个则会报错。

（1）各域支持的字符类型
    秒：可出现", - * /"四个字符，有效范围为0-59的整数
    分：可出现", - * /"四个字符，有效范围为0-59的整数
    时：可出现", - * /"四个字符，有效范围为0-23的整数
    每月第几天：可出现", - * / ? L W C"八个字符，有效范围为0-31的整数
    月：可出现", - * /"四个字符，有效范围为1-12的整数或JAN-DEc
    星期：可出现", - * / ? L C #"四个字符，有效范围为1-7的整数或SUN-SAT两个范围。1表示星期天，2表示星期一， 依次类推
（2）特殊字符含义
    * : 表示匹配该域的任意值，比如在秒*, 就表示每秒都会触发事件。；
    ? : 只能用在每月第几天和星期两个域。表示不指定值，当2个子表达式其中之一被指定了值以后，为了避免冲突，需要将另一个子表达式的值设为“?”；
    - : 表示范围，例如在分域使用5-20，表示从5分到20分钟每分钟触发一次
    / : 表示起始时间开始触发，然后每隔固定时间触发一次，例如在分域使用5/20,则意味着5分，25分，45分，分别触发一次.
    , : 表示列出枚举值。例如：在分域使用5,20，则意味着在5和20分时触发一次。
    L : 表示最后，只能出现在星期和每月第几天域，如果在星期域使用1L,意味着在最后的一个星期日触发。
    W : 表示有效工作日(周一到周五),只能出现在每月第几日域，系统将在离指定日期的最近的有效工作日触发事件。注意一点，W的最近寻找不会跨过月份
    LW : 这两个字符可以连用，表示在某个月最后一个工作日，即最后一个星期五。
    # : 用于确定每个月第几个星期几，只能出现在每月第几天域。例如在1#3，表示某月的第三个星期日。
（3）表达式例子
       0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
       0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时
       0 0 12 ? * WED 表示每个星期三中午12点
       "0 0 12 * * ?" 每天中午12点触发
       "0 15 10 ? * *" 每天上午10:15触发
       "0 15 10 * * ?" 每天上午10:15触发
       "0 15 10 * * ? *" 每天上午10:15触发
       "0 15 10 * * ? 2005" 2005年的每天上午10:15触发
       "0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发
       "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发
       "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
       "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发
       "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发
       "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发
       "0 15 10 15 * ?" 每月15日上午10:15触发
       "0 15 10 L * ?" 每月最后一日的上午10:15触发
       "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发
       "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发
       "0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
 */
@Component
public class SmsSchedule {

    @Autowired
    SmsService smsService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    //每分钟
    @Scheduled(cron = "0 0/1 * * * ?")
    public void scanSms(){

        log.info("begin deal sms info ...");
        smsService.dealSms();

    }
}
