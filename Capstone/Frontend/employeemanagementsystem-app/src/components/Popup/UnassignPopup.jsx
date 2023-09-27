import React from 'react'
import "./UnassignPopup.css";

const UnassignPopup = ({ description, onClose, onUnassign }) => {
  return (
    <div className="UA-project-popup">
      <div className="UA-project-popup-content">
        <p className='UA-popup-description'>{description}</p>
        <button className="UA-Accept-button" onClick={onUnassign}>
          Yes, I do
        </button>
        <button className="UA-closeee-button" onClick={onClose}>
          Close
        </button>
      </div>
    </div>
  );
};

export default UnassignPopup;
