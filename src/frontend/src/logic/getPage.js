const axios = require('axios')
const cheerio = require('cheerio')
async function getRequest(url) {
    let res = await axios.get(url)

    let data = res.data
    const $ = cheerio.load(data)
    console.log($('ul').text())
}

getRequest('https://semartinez.com')