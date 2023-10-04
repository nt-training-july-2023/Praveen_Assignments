import React from "react";

const Select = ({
  options,
  value,
  onChange,
  className,
  onBlur,
  placeholder,
}) => {
  return (
    <select
      value={value}
      onChange={onChange}
      className={className}
      onBlur={onBlur}
    >
      {placeholder !== undefined && (
        <option value="" disabled>
          {placeholder}
        </option>
      )}
      {/* {<option value="" disabled>
        {placeholder}
        </option>} */}
      {options.map((option) => (
        <option key={option.value} value={option.value}>
          {option.label}
        </option>
      ))}
    </select>
  );
};

export default Select;
