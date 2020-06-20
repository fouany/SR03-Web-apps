import React from 'react';
import './App.css';
import Calendar from './components/Calendar.js';

//var logins = []; Pour afficher plusieurs edt simultanément, on stocke les logins dans un tableau

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

    componentDidMount(login) {
        fetch('https://cors-anywhere.herokuapp.com/https://webapplis.utc.fr/Edt_ent_rest/myedt/result/?login=' + login)
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        uvs: result
                    });
                },
                // Remarque : il est important de traiter les erreurs ici au lieu d'utiliser un bloc catch(),
                // pour ne pas passer à la trappe des exceptions provenant de réels bugs du composant.
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    render() {
        console.log(this.state.uvs);
        if (this.state.uvs.length === 0) {
           return (
                   <div>
                       <form>
                           <label> Identifiant UTC : </label>
                           <input type="text" name="login" onChange={e => this.updateLogin(e)}/>
                           <button type="submit" value="Submit" onClick={(e) => this.changeIsLoginSubmitted(e)}>Afficher</button>
                       </form>
                       <p> Veuillez renseigner un identifiant UTC valide</p>
                   </div>
               );
        } else {
            return (
                <div>
                    <form>
                        <label> Identifiant UTC : </label>
                        <input type="text" name="login" onChange={e => this.updateLogin(e)}/>
                        <button type="submit" value="Submit" onClick={(e) => this.changeIsLoginSubmitted(e)}>Afficher</button>
                    </form>

                    <Calendar uvs={this.state.uvs}/>
                    {/*<Calendar uvs={this.state.uvs}/>*/}

                    {/*<Calendar uvs={this.state.uvs}/>*/}
                </div>
            )
        }
    }

    updateLogin = (e) => {
        this.setState({
            [e.target.name]: e.target.value // On récupère la valeur de l'input (le login) pour l'actualiser dans le state
        });
        console.log("login : ", this.state.login);
    };

    changeIsLoginSubmitted = (e) => {
        e.preventDefault();

        this.setState({ isLoginSubmitted: true});
        console.log("after : ", this.state.login);
        this.componentDidMount(this.state.login);

        // Si l'on souhaitait afficher plusieurs edt simultanément, il suffirait d'ajouter les les logins dans
        // la variable globale logins
        // Ensuite, on appelle componentDidMount() pour chaque login.
        // logins.push(this.state.login);
        // for (let i = 0; i < logins.length; i++) {
        //     console.log("tab login : ", logins[i]);
        //     this.componentDidMount(logins[i]);
        // }
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