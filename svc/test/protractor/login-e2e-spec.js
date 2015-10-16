describe('PAMM protractor simple example', function() {

    it('Should navigate to dashboard on successful login', function() {
        login("admin", "admin");
        expect(browser.getCurrentUrl()).toEqual('http://localhost:8080/#/login');
        element(by.id('login')).click();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:8080/#/dashboard');
    });

    it('Should navigate to add new project when clicked', function() {
        login("admin", "admin");
        element(by.id('login')).click();
        clickAddNewProject();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:8080/#/project/add');
    });

    it('Should add a new work item', function() {
        login("admin", "admin");
        element(by.id('login')).click();
        clickAddNewProject();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:8080/#/project/add');
        addNewProject("mock title", "mock summary", "mock info");
        closeModal('button-close');
        expect(browser.getCurrentUrl()).toEqual('http://localhost:8080/#/project');
        expect(browser.getCurrentUrl()).toEqual('http://localhost:8080/#/project');
        var todoList = element.all(by.repeater('project in projectCtrl.projects'));
        expect(todoList.count()).toEqual(4);
    });

    function login(username, password){
        browser.get('http://localhost:8080');
        element(by.id('username')).sendKeys(username);
        element(by.id('password')).sendKeys(password);
    }
    
    function clickAddNewProject(){
        element(by.xpath('/html/body/div/header/ul/li[2]/a')).click();
        element(by.xpath('/html/body/div/header/ul/li[2]/ul/li[2]/a')).click();
    }

    function addNewProject(title, summary, info){
        element(by.id('title')).sendKeys(title);
        element(by.id('summary')).sendKeys(summary);
        element(by.id('info')).sendKeys(info);
        element(by.xpath('/html/body/div/div/div/form/div[2]/button[1]')).click();
    }

    function closeModal(buttonId){
        var closeButton = element(by.id(buttonId));
        browser.wait(EC.visibilityOf(closeButton), 1000);
        closeButton.click();
    }
});