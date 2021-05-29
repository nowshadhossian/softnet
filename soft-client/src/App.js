import logo from './logo.svg';
import './App.css';
import {Route, Switch} from "react-router-dom";
import React from "react";
import StudyList from "./study/StudyList";
import StudyCreate from "./study/StudyCreate";

function App() {
  return (
      <Switch>
        <Route exact path="/" component={StudyList} />
        <Route path={"/study/list"} component={StudyList} />
        {/*<Route path={"/gateway/create"} component={GatewayCreate} />*/}
        <Route path={"/study/:id"} component={StudyCreate} />
        <Route path={"/study/create"} component={StudyCreate} />
      </Switch>
  );
}

export default App;
