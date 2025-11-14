import React from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import { saveAs } from 'file-saver';

class DashboardComponent extends React.Component {
    state = {
        data: []
    };

    componentDidMount() {
        axios.get('/api/dashboard/data')
            .then(response => {
                this.setState({ data: response.data });
            })
            .catch(error => {
                console.error('There was an error fetching the data!', error);
            });
    }

    exportToCSV = () => {
        const csvHeaders = 'ID, Description, Value\n';
        const csvRows = this.state.data.map(row => `${row.id}, ${row.description}, ${row.value}`).join('\n');
        const csvContent = csvHeaders + csvRows;

        const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
        saveAs(blob, 'dashboard_data.csv');
    }

    render() {
        return (
            <div className="container mt-5">
                <h2>Analytical Dashboard</h2>
                <div className="card mt-3">
                    <div className="card-header">Data Overview</div>
                    <div className="card-body">
                        <table className="table table-bordered">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Description</th>
                                    <th>Value</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.state.data.map((item, index) => (
                                    <tr key={index}>
                                        <td>{item.id}</td>
                                        <td>{item.description}</td>
                                        <td>{item.value}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                        <button className="btn btn-primary mt-3" onClick={this.exportToCSV}>Export to CSV</button>
                    </div>
                </div>
            </div>
        );
    }
}

export default DashboardComponent;