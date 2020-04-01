import React from 'react';
import { Table } from "react-bootstrap";

class JournalTable extends React.Component {
    render() {
        return <Table bordered>
            <thead>
            <tr>
                <th>ID студента</th>
                <th>ФИО</th>
                {this.props.subjects.map((subj, index) => {
                    return <th key={subj.index}>{subj.short_name} ({subj.type})</th>
                 })}
            </tr>
            </thead>
            <tbody>
            {this.props.marks.map((student, index) => {
                return <tr key={student.id}>
                    <td>{student.id}</td>
                    <td>{student.surname} {student.name} {student.secondName}</td>
                        {this.props.subjects.map((subj, i) => {
                            return student.marks[subj.index] ? <td style = {student.marks[subj.index].mark_id == 4 || student.marks[subj.index].mark_id == 6? {background:'#ff4b4b', color: "white"} : {}}>
                                {student.marks[subj.index].value} { student.marks[subj.index].mark_id == 4 || student.marks[subj.index].mark_id == 6 ? <span> - количество пересдач: {student.marks[subj.index].count}</span> : null} </td>:  <td></td>
                        })}
                </tr>
            })}
            </tbody>
        </Table>
    }
}

export default JournalTable;