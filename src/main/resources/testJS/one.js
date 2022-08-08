function greet(){
    let text = ""
    let day = new Date()
    let time = day.getHours()
    if ((time >= 0) && (time < 7))
        text = "夜猫子，要注意身体哦！ "
    if ((time >= 7) && (time < 12))
        text = "今天的阳光真灿烂啊，你那个朋友呢？"
    if ((time >= 12) && (time < 14))
        text = "午休时间。您要保持睡眠哦！"
    if ((time >= 14) && (time < 18))
        text = "祝您下午工作愉快！ "
    if ((time >= 18) && (time <= 22))
        text = "您又来了，可别和MM聊太久哦！"
    if ((time >= 22) && (time < 24))
        text = "您应该休息了！"
    console.log("===" + day + "===")
    console.log(text)
}

greet()

// //ScriptEngineManager 只能使用JS标准库
// function greet() {
//     return "A";
// }