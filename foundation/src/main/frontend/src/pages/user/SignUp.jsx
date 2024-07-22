import React, { useState } from "react";
import "../../css/SignUp.css";
import Postcode from "../../components/user/PostCode";

export default function SignUp() {
  const emailList = ["gmail.com", "naver.com", "hanmail.net", "daum.net"];

  const [address, setAddress] = useState("");
  const [selected, setSelected] = useState("");

  const handleAddressChange = (address) => {
    setAddress(address);
  };
  const handleEmailSelect = (e) => {
    setSelected(e.target.value);
  };

  return (
    <div className="signupPage">
      <div className="titleWrap">회원가입</div>

      {/* 회원가입 폼 */}
      <div className="signup_contentWrap">
        <div className="signup_inputTitle">아이디</div>
        {/* <div className="errorMessageWrap">사용할 수 없는 아이디입니다</div> */}
        <div className="signup_inputWrap">
          <input type="text" className="input" />
          <button className="checkButton">중복 확인</button>
        </div>
        <div className="signup_inputTitle">비밀번호</div>
        <div className="signup_inputWrap">
          <input type="password" className="input" />
        </div>
        <div className="signup_inputTitle">비밀번호 확인</div>
        <div className="signup_inputWrap">
          <input type="password" className="input" />
        </div>
        <div className="signup_inputTitle">이름</div>
        <div className="signup_inputWrap">
          <input type="text" className="input" />
        </div>
        <div className="signup_inputTitle">주소 검색</div>
        <div className="addressWrap">
          <div className="signup_addressInputWrap">
            <input type="text" className="input" readOnly value={address} />
          </div>
          <Postcode onAddressChange={handleAddressChange} />
        </div>
        <div className="signup_inputTitle">전화번호</div>
        <div>
          <div className="signup_phoneInputWrap">
            <input type="text" className="input" maxLength={3} />
          </div>
          &nbsp;-&nbsp;
          <div className="signup_phoneInputWrap">
            <input type="text" className="input" maxLength={4} />
          </div>
          &nbsp;-&nbsp;
          <div className="signup_phoneInputWrap">
            <input type="text" className="input" maxLength={4} />
          </div>
        </div>

        <div className="signup_inputTitle">이메일</div>
        <div className="signup_emailWrap">
          <div className="signup_emailInputWrap">
            <input type="text" className="input" />
          </div> &nbsp;@&nbsp;
          <div className="signup_emailList">
            <select
              className="selectEmailWrap"
              onChange={handleEmailSelect}
              value={selected}
            >
              {emailList.map((item) => (
                <option value={item} key={item}>
                  {item}
                </option>
              ))}
            </select>
          </div>
        </div>
      </div>

      {/* 회원가입 버튼 */}
      <div>
        <button className="joinButton">가입하기</button>
      </div>
    </div>
  );
}
