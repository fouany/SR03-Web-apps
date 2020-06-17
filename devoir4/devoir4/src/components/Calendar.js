import React, { Component } from 'react'

import Timetable from 'react-timetable-events'
import moment from 'moment';
import Test from "../App";


class Calendar extends Component {

    getInformation(stringify, day){
        var uvsByDay = [];
        for (let i = 0; i < stringify.length; i++) {
            console.log(i);
            console.log(stringify[i]);
            if (stringify[i].day.valueOf() == day.toString()){
                console.log("get information : ", stringify[i].day);
                uvsByDay.push(stringify[i]);
            }
        }
        console.log("UVSBYDAY : ", uvsByDay);
        return uvsByDay;
    }

    render () {
        let json = JSON.stringify(this.props.uvs);
        // console.log("JSON : ", json);
        // console.log("JSON attribute : ", json["uv"]);
        var stringify = JSON.parse(json);

        var days = {"lundi":'2018-02-23T', "mardi":'2018-02-24T', "mercredi":'2018-02-25T', "jeudi":'2018-02-26T', "vendredi":'2018-02-27T'};

        let coursLundi = this.getInformation(stringify, "LUNDI");
        let coursMardi = this.getInformation(stringify, "MARDI");
        let coursMercredi = this.getInformation(stringify, "MERCREDI");
        let coursJeudi = this.getInformation(stringify, "JEUDI");
        let coursVendredi = this.getInformation(stringify, "VENDREDI");


        this.state = {
            events: {
                monday :[
                    {
                        //id: 1,
                        //name: this.getInformation(stringify, "uv"),
                        name : 'coursLundi[0].day',
                        type: 'custom',
                        startTime: moment('2018-02-23T11:30:00'),
                        //startTime: moment(days.lundi + this.getInformation(stringify, "uv")),
                        //endTime: moment('2018-02-23T13:30:00')
                        endTime: moment('2018-02-23T13:30:00')
                    }
                ]
            }
        };

       // console.log(this.props.uvs);
        console.log("stringify", stringify);



        // var obj = '[{"availability_id":"109465","date":"2017-02-21","price":"430000"},{"availability_id":"109466","date":"2017-02-22","price":"50000000"},{"availability_id":"109467","date":"2017-02-23","price":"6666666666"}]';
        // var stringify = JSON.parse(obj);
        // for (var i = 0; i < stringify.length; i++) {
        //     console.log(stringify[i]['price']);
        // }
        //console.log(this.state.uvs.events[0]);
        //this.setState({events : this.props.uvs});
        return <Timetable events={this.state.events}/>

    }


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