import React from 'react';
import './App.css';
import Test from './components/Test.js';
import Calendar from './components/Calendar.js';


class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            login: null,
            error: null,
            isLoaded: false,
            isLoginSubmitted: false,
            uvs: []
        };
    }

    componentDidMount() {
        //id = 'aherkens';
        fetch('https://cors-anywhere.herokuapp.com/https://webapplis.utc.fr/Edt_ent_rest/myedt/result/?login=' + this.state.login)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        uvs: result
                    });
                },
                // Remarque : il est important de traiter les erreurs ici au lieu d'utiliser un bloc catch(), pour ne pas passer à la trappe des exceptions provenant de réels bugs du composant.
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    render() {
        //const listehtml = this.getuvliste();
        //console.log(this.state.uvs);

        if (this.state.isLoginSubmitted) {
            return <Calendar uvs={this.state.uvs}/>
        } else {
            return (
                <div>
                    <form>
                        <label> Identifiant UTC: </label>
                        <input type="text" name="login" onChange={e => this.updateLogin(e)}/>
                        <button type="submit" value="Submit" onClick={(e) => this.changeIsLoginSubmitted(e)}>Afficher</button>
                    </form>
                </div>
            )
        }
    }

    updateLogin = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        });
        console.log("login : ", this.state.login);
    };

    changeIsLoginSubmitted = (e) => {
        e.preventDefault();
        console.log("before : ", this.state.login);
        //console.log("target : ", e.target.value);

        this.setState({ isLoginSubmitted: true});
        console.log("after : ", this.state.login);
        this.componentDidMount();
    };

    getuvliste() {
        const {error, isLoaded, uvs} = this.state;
        if (error) {
            return <div>Erreur : {error.message}</div>;
        } else if (!isLoaded) {
            return <div>Chargement…</div>;
        } else {

            return (
                <div>
                    <h1> Liste des Uvs </h1>
                    <ul>
                        {uvs.map(item => (
                            <li key={item.uv + item.type}>{item.uv}</li>
                        ))}

                    </ul>
                </div>
            );
        }
    }
}


export default App;