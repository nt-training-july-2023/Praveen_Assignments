import React from 'react';
import "./Popup.css";

const Popup = ({ description, onClose }) => {
  return (
    <div className="project-popup">
      <div className="project-popup-content">
        <p className='popup-description'>{description}</p>
         <button className="closeee-button" onClick={onClose}>
          X
          </button>
      </div>
    </div>
  );
};

export default Popup;