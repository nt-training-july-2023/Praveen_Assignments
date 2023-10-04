import React from 'react';

const Input = ({ type, placeholder, value, onChange, className, onBlur }) => {
  return (
    <input
      type={type}
      placeholder={placeholder}
      value={value}
      onChange={onChange}
      className={className}
      onBlur={onBlur}
    />
  );
};

export default Input;
