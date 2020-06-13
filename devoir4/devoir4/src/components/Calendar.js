import React, { Component } from 'react'

import Timetable from 'react-timetable-events'
import moment from 'moment';
import Test from "../App";


class Calendar extends Component {


    getInformation(stringify, key){
        for (var i = 0; i < stringify.length; i++) {
            console.log("get information : ", stringify[i][key]);
        }
    }


    render () {

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

        let json = JSON.stringify(this.props.uvs);
        // console.log("JSON : ", json);
        // console.log("JSON attribute : ", json["uv"]);
        var stringify = JSON.parse(json);

        this.state = {
            events: {
                monday :[
                    {
                        id: 1,
                        //name: this.getInformation(stringify, "uv"),
                        name : '',
                        type: 'custom',
                        startTime: moment('2018-02-23T11:30:00'),
                        endTime: moment('2018-02-23T13:30:00')
                    }
                ]
            }
        };


        //console.log("Is array : ", (Array.isArray(this.props.uvs)));
        //console.log(this.props.uvs[0]["uv"]);

        //const imgSrcArray = obj.artists.artist.image.map( image => image['#text'] );

        console.log(this.props.uvs);

       // console.log("BLABLA", this.getInformation(this.state.json, "uv"));

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
