import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { BarChart, Bar, XAxis, YAxis, Tooltip, Legend, CartesianGrid, ResponsiveContainer } from 'recharts';
import Button from '@mui/joy/Button';

const AdvertisementDashboard: React.FC = () => {
    const [data, setData] = useState([]);
    const [sortedField, setSortedField] = useState('name'); 
    const [sortOrder, setSortOrder] = useState('asc'); 

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = () => {
        axios.get('http://localhost:8080/api/advertisements/getAll')
            .then(response => setData(response.data))
            .catch(error => console.error('Error fetching data:', error));
    };

    const handleSort = (field: string) => {

        if (sortedField === field) {
            setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
        } else {
            setSortedField(field);
            setSortOrder('asc');
        }
    };

    const sortedData = [...data].sort((a, b) => {
        const comparison = sortOrder === 'asc' ? 1 : -1;

        if (a[sortedField] < b[sortedField]) {
            return -comparison;
        }
        if (a[sortedField] > b[sortedField]) {
            return comparison;
        }
        return 0;
    });

    return (
        <div style={{ width: '100%', height: 400 }}>
            <h1 style={{marginLeft: "50px"}}>Advertising Performance Dashboard</h1>
            <div style={{ marginBottom: '10px' }}>
            <Button 
                className='sortName' 
                style={{ marginTop: '10px', marginLeft: '50px' }} 
                variant="outlined" 
                onClick={() => handleSort('name')}
            >
                Sort by Name
            </Button>
            <Button 
                className='sortImpressions' 
                style={{ marginTop: '10px', marginLeft: '50px' }} 
                variant="outlined" 
                onClick={() => handleSort('impressions')}
            >
                Sort by Impressions
            </Button>
            <Button 
                className='sortClicks' 
                style={{ marginTop: '10px', marginLeft: '50px' }} 
                variant="outlined" 
                onClick={() => handleSort('clicks')}
            >
                Sort by Clicks
            </Button>
            <Button 
                className='sortCost' 
                style={{ marginTop: '10px', marginLeft: '50px' }} 
                variant="outlined" 
                onClick={() => handleSort('cost')}
            >
               Sort by Cost
            </Button>
            </div>
            <ResponsiveContainer>
                <BarChart data={sortedData} margin={{ top: 20, right: 30, left: 20, bottom: 5 }}>
                    <CartesianGrid strokeDasharray="3 3" />
                    <XAxis dataKey="name" />
                    <YAxis />
                    <Tooltip />
                    <Legend />
                    <Bar dataKey="impressions" fill="#8884d8" />
                    <Bar dataKey="clicks" fill="#82ca9d" />
                    <Bar dataKey="cost" fill="#ffc658" />
                </BarChart>
            </ResponsiveContainer>
        </div>
    );
};

export default AdvertisementDashboard;
