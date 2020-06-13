import React from "react";

class MyApp extends React.Component {
    render() {
        var json = {"active":{"label":"Active","value":"12"},"automatic":{"label":"Automatic","value":"8"},"waiting":{"label":"Waiting","value":"1"},"manual":{"label":"Manual","value":"3"}};
        var arr = [];
        Object.keys(json).forEach(function(key) {
            arr.push(json[key]);
        });
        return <ul>{arr.map(item => <MyAppChild key={item.label} label={item.label} value={item.value} />)}</ul>;
    }
}

class MyAppChild extends React.Component {
    render() {
        return <li>{this.props.label + " - " + this.props.value}</li>;
    }
}

ReactDOM.render(<MyApp />, document.getElementById('myapp'));