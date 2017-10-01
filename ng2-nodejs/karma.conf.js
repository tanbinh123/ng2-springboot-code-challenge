module.exports = function (config) {
    var appBase    = 'public/';       // transpiled app JS and map files
    var _config = {

        plugins: [
            'karma-jasmine',
            'karma-typescript',
            'karma-chrome-launcher',
            'phantomjs-prebuilt',
            'karma-phantomjs-launcher'
        ],

        frameworks: [ 'jasmine', 'karma-typescript' ],

        files: [
            // System.js for module loading
            'node_modules/systemjs/dist/system.src.js',

            // Polyfills
            'node_modules/core-js/client/shim.js',

            // zone.js
            'node_modules/zone.js/dist/zone.js',
            'node_modules/zone.js/dist/long-stack-trace-zone.js',
            'node_modules/zone.js/dist/proxy.js',
            'node_modules/zone.js/dist/sync-test.js',
            'node_modules/zone.js/dist/jasmine-patch.js',
            'node_modules/zone.js/dist/async-test.js',
            'node_modules/zone.js/dist/fake-async-test.js',

            // RxJs
            { pattern: 'node_modules/rxjs/**/*.js', included: false, watched: false },
            { pattern: 'node_modules/rxjs/**/*.js.map', included: false, watched: false },

            // Paths loaded via module imports:
            // Angular itself
            { pattern: 'node_modules/@angular/**/*.js', included: false, watched: false },
            { pattern: 'node_modules/@angular/**/*.js.map', included: false, watched: false },

            { pattern: appBase + '/systemjs.config.js', included: false, watched: false },
            { pattern: appBase + '/systemjs.config.extras.js', included: false, watched: false },

            { pattern: 'index.spec.ts' },
            { pattern: 'public/**/*.+(ts|html)' },
            { pattern: 'public/**/*.+(spec.ts)' }
        ],

        exclude: [ 'public/app/main.ts' ],

        preprocessors: {
            "**/*.ts": [ 'karma-typescript' ]
        },

        karmaTypescriptConfig: {
            tsconfig: './tsconfig.test.json'
        },

        reporters: [ 'progress', 'karma-typescript' ],
        port: 9876,
        colors: true,
        logLevel: config.LOG_DEBUG,
        autoWatch: false,
        browsers: [ 'Chrome' ],
        singleRun: true
    };

    config.set(_config);
};
