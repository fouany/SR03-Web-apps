import React, {Component} from 'react'
import Timetable from 'react-timetable-events'
import moment from 'moment';

/**
 * On attribue une date choisie pour chaque jour de la semaine, afin de s'adapter au format de la librairie
 * @type {{lundi: string, vendredi: string, mercredi: string, jeudi: string, mardi: string}}
 */
const days = {
    "lundi": '2018-02-23T',
    "mardi": '2018-02-24T',
    "mercredi": '2018-02-25T',
    "jeudi": '2018-02-26T',
    "vendredi": '2018-02-27T'
};

// Contient les différentes couleurs des UVs
var colorUv = {};

/**
 * Fonction qui choisie aléatoirement la couleur en code hexadécimal
 * @returns {string}
 */
function randomColor(){
    let x = Math.round(0xffffff * Math.random()).toString(16);
    let y = (6 - x.length);
    let z = "000000";
    let z1 = z.substring(0, y);
    return "#" +z1 + x;
}

/**
 * Attribue une couleur (aléatoirement choisie) à tous les enseignements d'une UV.
 * Chaque UV aura une couleur différente.
 * @param event
 * @returns {*}
 */
function chooseColor(event) {
    if (colorUv[event.name]) {
        return colorUv[event.name];
    } else {
        return colorUv[event.name] = randomColor();
    }
}

class Calendar extends Component {

    /**
     * Récupère les informations (non formattées) des UVS par jour
     * @param stringify
     * @param day
     * @returns {Array}
     */
    getInformation(stringify, day) {
        let uvsByDay = [];
        for (let i = 0; i < stringify.length; i++) {
            if (stringify[i].day.valueOf() == day.toString()) {
                uvsByDay.push(stringify[i]);
            }
        }
        return uvsByDay;
    }

    /**
     * Formatte les UVS par jour au format de la librairie
     * @param uvsByDay
     * @param day
     * @returns {Array}
     */
    formatByDay(uvsByDay, day) {
        const uvsFormatees = [];
        for (let i = 0; i < uvsByDay.length; i++) {
            const coursI = {
                name: uvsByDay[i].uv,
                info: uvsByDay[i].room + " " + uvsByDay[i].type + " " + uvsByDay[i].group,
                startTime: moment(day + uvsByDay[i].begin),
                endTime: moment(day + uvsByDay[i].end)
            }
            uvsFormatees.push(coursI);
        }
        return uvsFormatees;
    }

    /**
     * Permet d'ajouter les couleurs au calendrier
     * @param event
     * @param defaultAttributes
     * @param styles
     * @returns {*}
     */
    renderEvent(event, defaultAttributes, styles) {
        // Attribue les couleurs
        defaultAttributes.style['backgroundColor'] = chooseColor(event);
        return (
            <div {...defaultAttributes}
                 title={event.name}
                 key={event.id}>
        <span className={styles.event_info}>
          [ {event.name}] <br/>
            {event.info}
        </span>
                <span className={styles.event_info}>
          {event.startTime.format('HH:mm')} - {event.endTime.format('HH:mm')}
        </span>
            </div>
        )
    }


    render() {
        if (this.props.uvs.length) {
            let json = JSON.stringify(this.props.uvs);

            const stringify = JSON.parse(json); // On récupère le json des uvs

            // On récupère les UVS par jour afin de faciliter/atomiser le formattage
            const coursLundi = this.getInformation(stringify, "LUNDI");
            const coursMardi = this.getInformation(stringify, "MARDI");
            const coursMercredi = this.getInformation(stringify, "MERCREDI");
            const coursJeudi = this.getInformation(stringify, "JEUDI");
            const coursVendredi = this.getInformation(stringify, "VENDREDI");

            // On actualise le calendrier avec les données de l'API formattées pour la librairie du calendrier
            this.state = {
                events: {
                    monday: this.formatByDay(coursLundi, days.lundi),
                    tuesday: this.formatByDay(coursMardi, days.mardi),
                    wednesday: this.formatByDay(coursMercredi, days.mercredi),
                    thursday: this.formatByDay(coursJeudi, days.jeudi),
                    friday: this.formatByDay(coursVendredi, days.vendredi)
                }
            };

            // Affichage du calendrier avec les évènements (UVS) mis à jour auparavant
            return <Timetable events={this.state.events} hoursInterval={[7, 21]} renderEvent={this.renderEvent}/>
        }

        // Ce calendrier test sert seulement à ce que le state ne soit pas null
        this.state = {
            events: {
                monday: [
                    {
                        name: 'test',
                        type: 'custom',
                        startTime: moment('2018-02-23T11:30:00'),
                        endTime: moment('2018-02-23T13:30:00')
                    }
                ]
            }
        };

        return <Timetable events={this.state.events}/>
    }
}

export default Calendar;