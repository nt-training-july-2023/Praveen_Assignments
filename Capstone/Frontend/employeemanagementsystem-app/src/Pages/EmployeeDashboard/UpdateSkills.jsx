import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import SkillsDropDown from '../../components/Dropdowns/Skills';
import { useParams } from 'react-router-dom';
import MultipleSelectDropdown from '../../components/MultipleSelectDropdown/MultipleSelectDropdown';
import axios from 'axios';

function UpdateSkills() {
  const [skills, setSkills] = useState([]);
  const [employeeDetails, setEmployeeDetails] = useState([]);
  const [updateSkillsError, setUpdateSkillsError] = useState('');
  const { employeeId } = useParams();
  const [selectedSkills, setSelectedSkills] = useState([]);
  const [fetchData, setFetchData] = useState(false)
  const  email = localStorage.getItem("email"); 

  const navigate = useNavigate();

  const handleSkillChange = (selectedOptions) => {
    const selectedSkillsValues = selectedOptions.map((option) => option.value);
    setSkills(selectedSkillsValues);
  };


  useEffect(() => {
    getEmployeeData();
  }, []);

  useEffect(() => {
    // When employeeDetails change, update selectedSkills with employee's skills
    console.log(employeeDetails.skills)
    if (employeeDetails.skills) {
      setSelectedSkills(employeeDetails.skills);
    }
  }, [employeeDetails]);



  const ValidateSkill = () => {
    if (skills.length === 0) {
      setUpdateSkillsError('please select');
    } else {
      setUpdateSkillsError('');
    }
  };

  const getEmployeeData = async() =>{
    try{
        const response = await axios.get(`http://localhost:8080/api/employee/email/${email}`);
        setEmployeeDetails(response.data);
        setTimeout(() => {
          setFetchData(true);
        }, 200);
    }
    catch(error){
        console.log(error);
    }
  }

  const handleUpdate = async () => {
    ValidateSkill();
    const toastId = 'handleUpdate';
    if (updateSkillsError) {
      toast.error('Please Update Skills', {
        position: 'top-right',
        autoClose: 1000,
        toastId,
      });
    } else {
      try {
        const response = await axios.put(`http://localhost:8080/api/updateSkill/${employeeId}`, {
          skills: skills,
        });

        console.log('Employee updated:', response.data);
        navigate('/EmployeeDashboard');
      } catch (error) {
        console.error('Error updating employee:', error);
      }
    }
  };

  const handleCancel = (e) => {
    navigate('/EmployeeDashboard');
  };

  return (
    <div>
      <div>
        <h1 className="AP">Update Skills</h1>
      </div>
      <br />
      <div className="AP-container">
        <h2 style={{ fontWeight: 'bold' }}>{employeeDetails.name}</h2>

        <div className="form-group">
          <label className="form-label">Skills</label>
          {fetchData && <MultipleSelectDropdown
            options={SkillsDropDown.map((skill) => ({
              value: skill,
              label: skill,
            }))}
            selectedOptions={selectedSkills.map((skill) => ({
              value: skill,
              label: skill,
            }))}
            onChange={handleSkillChange}
            placeholder="Select Skills"
          />}
        </div>

        <div className="AP-button-container">
          <button type="button" onClick={handleUpdate}>
            Update Skills
          </button>
          <button type="button" className="secondary" onClick={handleCancel}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
}

export default UpdateSkills;
