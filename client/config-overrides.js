const path = require('path')

const resolve = dir => path.resolve(__dirname, dir)

module.exports = (config, env) => {

    config.resolve.alias = Object.assign(config.resolve.alias, {
        '@notee/assets': resolve('src/assets'),
        '@notee/elements': resolve('src/elements'),
        '@notee/components': resolve('src/components'),
        '@notee/layout': resolve('src/layout'),
        '@notee/reducers': resolve('src/reducers'),
        '@notee/actions': resolve('src/actions'),
        '@notee/api': resolve('src/api')
    })

    return config
}
