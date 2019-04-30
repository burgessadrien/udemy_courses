class IndecisionApp extends React.Component {

    constructor(props) {
        super(props);
        this.handleDeleteAllOptions = this.handleDeleteAllOptions.bind(this);
        this.handlePick = this.handlePick.bind(this);
        this.handleAddOption = this.handleAddOption.bind(this);
        this.state = {
            pick: "",
            options: []
        };
    }

    handleDeleteAllOptions() {
        this.setState(() => {
            return {
                options: []
            };
        });
    }

    handlePick() {
        const rand = Math.floor(Math.Random() * this.state.length);
        const pick = this.state.options[rand];
        this.setState(() => {
            return {
                pick: pick
            };
        });
    }

    handleAddOption(option) {
        if(!option) {
            return 'Nonempty value required';
        }else if(this.state.options.indexOf(option) > -1) {
            return 'This option aready exists';
        }else {
            this.setState((prevState) => {
                return {
                    options: prevState.options.concat(option)
                };
            });
            return undefined;
        }
    }

    render() {
        const title = "Indecision";
        const subTitle = "Put your life in the hands of the computer!";

        return (
            <div>
                <Header 
                    title={title} 
                    subTitle={subTitle} 
                />

                <Action 
                    hasOption={this.state.options.length > 0} 
                    handlePick={this.handlepick}    
                />
               
                <Options 
                    options={this.state.options} 
                    handleDeleteAllOptions={this.handleDeleteAllOptions}
                />

                <AddOption 
                        handleAddOption={this.handleAddOption}
                />
            </div>
        );
    }
}

const Header = (props) => {
    return (
        <div>
            <h1>{props.title}</h1>
            <h2>{props.subTitle}</h2>
        </div>
    );
}

Header.defaultProps = {
    title: "Indecision",
    subTitle: "",
}

const Action = (props) => {
    return (
        <div>
            <button onClick={props.handlePick} disabled={props.hasOptions}>What should I do?</button>
        </div>
    );
}

const Options = (props) => {
    return (
        <div>
            <button onClick={props.handleDeleteAllOptions}>Remove All</button>
            {
                props.options.map((option) => <Option key={option} optionText={option} />)
            }
        </div>
    );
}

const Option = (props) => {
    return (
        <div>
            {props.optionText}
        </div>
    );
}

class AddOption extends React.Component {

    constructor(props) {
        super(props);
        this.handleAddOption = this.handleAddOption.bind(this);
        this.state = {
            error: undefined
        }
    }

    handleAddOption(e) {
        e.preventDefault(); 
        const option = e.target.elements.option.value.trim();
        if(option) {
            e.target.elements.option.value = "";
            const error = this.props.handleAddOption(option);
            this.setState(() => {
                return {
                    error : error
                };
            });
        }
    }

    render() {
        return (
            <div>
                {this.state.error && <p>{this.state.error}</p>}
                <form onSubmit={this.handleAddOption}>
                    <input type="text" placeholder="Option" name="option" />
                    <button>Add Option</button>
                </form>
            </div>
        );
    }
}

ReactDOM.render(<IndecisionApp />, document.getElementById("app")); 