export default {
    isSameDay(date1, date2) {
        const year1 = date1.getFullYear()
        const year2 = date2.getFullYear()
        const month1 = date1.getMonth() + 1
        const month2 = date2.getMonth() + 1
        const day1 = date1.getDay()
        const day2 = date2.getDay()
        if (year1 === year2 && month1 === month2 && day1 === day2) return true
        else return false
    }
}