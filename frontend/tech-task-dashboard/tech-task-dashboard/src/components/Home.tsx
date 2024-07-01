import { Button, Input } from '@mui/joy';
import React, { useState } from 'react';
import axios from 'axios';

const Home: React.FC = () => {
  const [name, setName] = useState('');
  const [impressions, setImpressions] = useState('');
  const [clicks, setClicks] = useState('');
  const [cost, setCost] = useState('');

  const handleSubmit = async () => {
    const data = {
      name: name,
      impressions: parseInt(impressions),
      clicks: parseInt(clicks),
      cost: parseFloat(cost),
    };

    try {
      const response = await axios.post('http://localhost:8080/api/advertisements/add', data);
      console.log('Data sent successfully:', response.data);
      setName('');
      setImpressions('');
      setClicks('');
      setCost('');
    } catch (error) {
      console.error('Error sending data:', error);
    }
  };

  return (
    <div className='data_adder'>
      <h1 style={{ marginLeft: '10px' }}>Send your Data</h1>
      <Input 
        className='name' 
        placeholder='Name' 
        value={name} 
        onChange={(e) => setName(e.target.value)}
      />
      <Input 
        className='impressions' 
        placeholder='Impressions' 
        value={impressions} 
        onChange={(e) => setImpressions(e.target.value)} 
      />
      <Input 
        className='clicks' 
        placeholder='Clicks' 
        value={clicks} 
        onChange={(e) => setClicks(e.target.value)} 
      />
      <Input 
        className='cost' 
        placeholder='Cost' 
        value={cost} 
        onChange={(e) => setCost(e.target.value)} 
      />
      <Button 
        className='submit_button' 
        style={{ marginTop: '10px', marginLeft: '310px' }} 
        variant="outlined" 
        onClick={handleSubmit}
      >
        Submit
      </Button>
    </div>
  );
};

export default Home;
