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
    },
    toNumberFormat(numstring) {
        numstring = Array(...(numstring.replace(/,/g, ''))).reverse()
        let result = Array()
        for (let i in numstring) {
            if (i % 3 === 0 && i != 0) {
                result.push(',')
            }
            result.push(numstring[i])
        }
        return result.reverse().join('')
    },
    toKorNumberFormat(numstring) {
        let temp = []
        numstring = Array(...(numstring.replace(/,/g, ''))).reverse()
        let result = Array()
        for (let i in numstring) {
            if (i == 4) {
                if ( temp ===['0','0','0','0']) {
                    temp = []
                } else {
                    result.push(temp.reverse().join(''))
                    temp = []
                }
                result.push('만 ')
            } else if (i == 8) {
                if (temp ===['0','0','0','0']) {
                    temp = []
                } else {
                    result.push(temp.reverse().join(''))
                    temp = []
                }
                result.push('억 ')
            }
            temp.push(numstring[i])
        }
        result.push(temp.reverse().join(''))
        return result.reverse().join('')
    }
}