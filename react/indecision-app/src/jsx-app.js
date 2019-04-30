// JSX - Javascript XML
 
const app = {
    title: "Indecision App",
    subtitle: "What should I do?",
    options: [],
};

let choice = "";

const randomChoice = () => {
    const rand = Math.floor(Math.random() * app.options.length);
    choice = app.options[rand];
    renderApp();
}

const addOption = (e) => {
    e.preventDefault();

    const option = e.target.elements.option.value;
    if(option) {
        app.options.push(option);
        e.target.option.value = "";
        renderApp();
    }
}

const removeOption = () => {

}

const clearOptions = () => {
    app.options = [];
    renderApp();
}

const appRoot = document.getElementById('app');

const renderApp = () => {
    const template = (
        <div>
            <h1>{app.title}</h1>
            {app.subtitle && <p>{app.subtitle}</p>}
            <button onClick={clearOptions}>Remove All</button>
            <p>Number of options: {app.options.length}</p>
            <p>{app.options.length > 0 ? 'Here are your options:' : 'You have no options!!!'}</p>
            <ol>
                {
                    app.options.map((option) => {
                        return <li><p>{option}</p><button>Remove</button></li>;
                    })
                }
            </ol>
            <form onSubmit={addOption}>
                <input type="text" name="option" placeholder="Please enter an option"></input>
                <button>Add Option</button>
            </form>

            <p>{choice}</p>
            {app.options.length > 0 &&<button onClick={randomChoice}>Random</button>}
        </div>
    );
    
    ReactDOM.render(template, appRoot);
}

//renderApp();
