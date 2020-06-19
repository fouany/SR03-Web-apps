import React, {Component} from 'react'

import Timetable from 'react-timetable-events'
import moment from 'moment';
import Test from "../App";


const days = {
    "lundi": '2018-02-23T',
    "mardi": '2018-02-24T',
    "mercredi": '2018-02-25T',
    "jeudi": '2018-02-26T',
    "vendredi": '2018-02-27T'
};

const colors = {

};

class Calendar extends Component {

    getInformation(stringify, day) {
        var uvsByDay = [];
        for (let i = 0; i < stringify.length; i++) {
            //console.log(i);
            //console.log(stringify[i]);
            if (stringify[i].day.valueOf() == day.toString()) {
                //console.log("get information : ", stringify[i].day);
                uvsByDay.push(stringify[i]);
            }
        }
        console.log("UVSBYDAY : ", uvsByDay);
        return uvsByDay;
    }

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

    renderEvent(event, defaultAttributes, styles) {
        return (
            <div {...defaultAttributes}
                 title={event.name}
                 key={event.id}>
        <span className={styles.event_info}>
          [ { event.name }] <br/>
            {event.info}
        </span>
                <span className={styles.event_info}>
          { event.startTime.format('HH:mm') } - { event.endTime.format('HH:mm') }
        </span>
            </div>
        )
    }

    render() {

        if (this.props.uvs.length) {
            let json = JSON.stringify(this.props.uvs);

            const stringify = JSON.parse(json);

            const coursLundi = this.getInformation(stringify, "LUNDI");
            let coursMardi = this.getInformation(stringify, "MARDI");
            let coursMercredi = this.getInformation(stringify, "MERCREDI");
            let coursJeudi = this.getInformation(stringify, "JEUDI");
            let coursVendredi = this.getInformation(stringify, "VENDREDI");

            this.state = {
                events: {
                    monday: this.formatByDay(coursLundi, days.lundi),
                    tuesday: this.formatByDay(coursMardi, days.mardi),
                    wednesday: this.formatByDay(coursMercredi, days.mercredi),
                    thursday: this.formatByDay(coursJeudi, days.jeudi),
                    friday: this.formatByDay(coursVendredi, days.vendredi)
                }
            };

            return <Timetable events={this.state.events} hoursInterval={[7, 21]} renderEvent={this.renderEvent}/>
        }

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

    // TODO : check variables
}

export default Calendar;


// this.state = {
//     events: {
//         monday: [
//             {
//                 id: 1,
//                 name: 'Custom Event 1',
//                 type: 'custom',
//                 startTime: moment('2018-02-23T11:30:00'),
//                 endTime: moment('2018-02-23T13:30:00')
//             }
//         ],
//         tuesday: [
//             {
//                 id: 2,
//                 name: 'Custom Event 2',
//                 type: 'custom',
//                 startTime: moment('2018-02-22T12:30:00'),
//                 endTime: moment('2018-02-22T14:30:00')
//             },
//             {
//                 id: 3,
//                 name: 'Custom Event 3',
//                 type: 'custom',
//                 startTime: moment('2018-02-22T16:30:00'),
//                 endTime: moment('2018-02-22T18:45:00')
//             }
//         ],
//         wednesday: [],
//         thursday: [],
//         friday: []
//     }
// };