exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['login-e2e-spec.js'],
    onPrepare: function () {
        global.EC = protractor.ExpectedConditions;
    }
};
