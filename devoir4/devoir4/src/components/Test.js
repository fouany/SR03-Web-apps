import React from 'react';

class Test extends React.Component {

    render() {
        if(this.props.uvs.length !== 0){
            console.log(this.props.uvs);
            return <span>{this.props.uvs[0].day}</span>;



        } return null;
    }
}

export default Test;

