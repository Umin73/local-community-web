import React from 'react';
import "../../css/Write.css";
import styled from "styled-components";

export default function Dropdown({ categoryId, onSelect }) {
    const categories = {
      1: { '4': '자유', '5': '정보', '6': '홍보' },
      2: { '7': '식당', '8': '카페·베이커리', '9': '의료', '10': '패션·미용', '11': '동물', '12': '교육', '13': '여가', '14': '기타' },
      3: { '15': '분실', '16': '실종', '17': '사고' }
    };

  const options = categories[categoryId] || [];

  return (
      <ul className="dropdownMenu">
        {Object.entries(options).map(([subCategoryId, name]) => (
          <li key={subCategoryId} onClick={() => onSelect(subCategoryId, name)}>
            {name}
          </li>
        ))}
      </ul>
    );
  }