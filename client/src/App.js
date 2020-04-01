import React from 'react';
import Request from "./services/Request";
import "bootstrap/dist/css/bootstrap.css";
import {  Button} from "react-bootstrap";
import JournalTable from "./components/JournalTable";

class App extends React.Component  {
  
    constructor() {
        super();
        this.state = {
            activeGroup: 0,
            students: null,
            groups: null,
            groupJournals: [],
            SubjectList: []
        };
    }

    setGroupJournal(id){
        if(this.state.groupJournals.id == null) {
            Request.getStudentsByStudyGroup(id).then((students) => {
                Request.getJournalBySTudyGroup(id).then((journals) => {
                    var temp_arr = [];
                    var subj_arr = [];
                    journals.forEach((item, i, arr) => {
                        if(!subj_arr[item.study_plan_id]){
                            subj_arr[item.study_plan_id] = {
                                "index":item.study_plan_id,
                                "full_name": item.full_name,
                                "short_name":item.short_name,
                                "type": item.type
                            };
                        }
                        if (!temp_arr[item.student_id]) temp_arr[item.student_id] = [];
                        temp_arr[item.student_id][item.study_plan_id]=item;
                    });
                    students.forEach((item, i, arr) => {
                        if (!temp_arr[item.id]) arr[i]["marks"] = [];
                        else arr[i]["marks"] = temp_arr[item.id];
                    });
                    var temp_st = this.state.groupJournals;
                    temp_st[id] = students;
                    var temp_sl = this.state.SubjectList;
                    temp_sl[id] = subj_arr;
                    this.setState({
                        groupJournals: temp_st,
                        SubjectList:temp_sl
                    });
                });
            });
        }
        this.setState({activeGroup: id});
    }

    componentDidMount() {
        Request.getAllGroups().then((groups) => {
            this.setGroupJournal(groups[0].id);
            this.setState({groups: groups});
        });
    }

    render() {
        return (
            <div className="App" style={{margin:'50px', width: "auto", textAlign: "center"}}>
                { this.state.groupJournals[this.state.activeGroup] ?
                    <JournalTable marks={this.state.groupJournals[this.state.activeGroup]} subjects={this.state.SubjectList[this.state.activeGroup]}/> : null}
                { this.state.groups ? this.state.groups.map((group) => (
                    <Button style={{margin:'15px'}}  key={group.id} onClick={() => {
                        this.setGroupJournal(group.id);
                    }}>{group.name}</Button>
                )) : null}
            </div>
        );
    }
}

export default App;
