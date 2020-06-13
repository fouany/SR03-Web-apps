import React from 'react';
import './App.css';
import Test from './components/Test.js';
import Calendar from './components/Calendar.js';



class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      uvs: []
    };
  }

  componentDidMount() {
    fetch('https://cors-anywhere.herokuapp.com/https://webapplis.utc.fr/Edt_ent_rest/myedt/result/?login=aherkens')
        .then(res => res.json())
        .then(
            (result) => {
              this.setState({
                isLoaded: true,
                uvs: result
              });
            },
            // Remarque : il est important de traiter les erreurs ici
            // au lieu d'utiliser un bloc catch(), pour ne pas passer à la trappe
            // des exceptions provenant de réels bugs du composant.
            (error) => {
              this.setState({
                isLoaded: true,
                error
              });
            }
        )
  }

  render() {
    const listehtml = this.getuvliste();
    console.log(this.state.uvs);
    //return <Calendar/>;
      return <div>
          <Calendar uvs={this.state.uvs}/>
          {listehtml}</div>;
   // return <Calendar uvs={listehtml}/>;
    // return <div>
    //   <Test uvs={this.state.uvs}/>
    //   {listehtml}</div>;
  }

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
