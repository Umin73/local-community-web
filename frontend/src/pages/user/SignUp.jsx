import React, { useEffect, useState } from "react";
import axios from "axios";
import "../../css/SignUp.css";
import Postcode from "../../components/user/PostCode";
import axiosInstance from "../../api/axiosInstance";

export default function SignUp() {

  // 초기값
  const emailList = ["gmail.com", "naver.com", "hanmail.net", "daum.net"];
  const initialFormValues = {
    id: "",
    pw: "",
    pwCheck: "",
    name: "",
    nickname: "",
    address: "",
    phone1: "",
    phone2: "",
    phone3: "",
    email: "",
    selected: emailList[0],
  };

  // 오류메세지
  const initialErrorMessages = {
    idMessage: "",
    idCheckMessage: "",
    pwMessage: "",
    pwCheckMessage: "",
    nameMessage: "",
    nicknameCheckMessage: "",
    phoneMessage: "",
  };

  // 유효성
  const [notAllow, setNotAllow] = useState(true);
  const initialValidationStates = {
    isId: false,
    isIdChecked: false,
    isPw: false,
    isPwCheck: false,
    isName: false,
    isNicknameChecked: false,
    isPhone1: false,
    isPhone2: false,
    isPhone3: false,
  };

  const [formValue, setFormValue] = useState(initialFormValues);
  const [errorMessage, setErrorMessage] = useState(initialErrorMessages);
  const [validationState, setValidationState] = useState(initialValidationStates);

  // 카카오 로그인 전용 토큰, useId
  const [token, setToken] = useState("");
  const [userId, setUserId] = useState("");

  const onChangeId = (e) => {
    const currentId = e.target.value;
    setFormValue({ ...formValue, id: currentId});
    const idRegExp = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{4,25}$/;

    if(!idRegExp.test(currentId)){
      setErrorMessage({ ...errorMessage, idMessage: "영문, 숫자 포함 4~25자"});
      setValidationState({ ...validationState, isId: false});
    } else {
      setErrorMessage({ ...errorMessage, idMessage: ""});
      setValidationState({ ...validationState, isId: true});
    }
  };

  const onChangePw = (e) => {
    const currentPw = e.target.value;
    setFormValue({...formValue, pw: currentPw});
    const pwRegExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,100}$/;

    if(!pwRegExp.test(currentPw)) {
      setErrorMessage({...errorMessage, pwMessage: "영문, 숫자, 특수문자 포함 8자 이상"});
      setValidationState({...validationState, isPw: false});
    } else {
      setErrorMessage({...errorMessage, pwMessage: ""});
      setValidationState({...validationState, isPw: true});
    }
  }

  const onChangePwCheck = (e) => {
    const currentPwCheck = e.target.value;
    setFormValue({ ...formValue, pwCheck: currentPwCheck});
    if(formValue.pw !== currentPwCheck) {
      setErrorMessage({ ...errorMessage, pwCheckMessage: "비밀번호가 일치하지 않습니다."});
      setValidationState({ ...validationState, isPwCheck: false});
    } else {
      setErrorMessage({ ...errorMessage, pwCheckMessage: "비밀번호가 일치합니다."});
      setValidationState({ ...validationState, isPwCheck: true});
    }
  };

  const onAlert = (message) => {
    alert(message);
  };

  // Base64 디코딩 (카카오 로그인 jwt 페이로드 추출용)
  const base64Decode = (str) => {
    return decodeURIComponent(
        atob(str)
            .split('')
            .map(c => `%${('00' + c.charCodeAt(0).toString(16)).slice(-2)}`)
            .join('')
    );
  };

  // JWT 토큰에서 userId 추출
  const parseJwt = (token) => {
    try {
      const base64Url = token.split('.')[1]; // payload 부분 추출
      const base64 = base64Decode(base64Url); // Base64 디코딩
      const payload = JSON.parse(base64); // JSON 파싱

      return payload.sub; // userId 추출  (sub필드에 userId 담겨있음)
    } catch (error) {
      console.error("토큰 파싱 중 에러 발생:", error);
      return null;
    }
  };

  useEffect(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const jwtToken = urlParams.get('token');

    if(jwtToken) {
      setToken(jwtToken);
      const extractedUserId = parseJwt(jwtToken);
      if(extractedUserId) {
        setUserId(extractedUserId);
        setFormValue(prevFormValue => ({
          ...prevFormValue,
          id: extractedUserId,
          pw: extractedUserId + "pw"
        }));
      }
    }
  }, []);

  useEffect(() => {
    if (formValue.pw === "" && formValue.pwCheck === "") {
      setErrorMessage(prevState => ({ ...prevState, pwCheckMessage: "" }));
      setValidationState(prevState => ({ ...prevState, isPwCheck: false }));
    } else if ((formValue.pwCheck && formValue.pw !== formValue.pwCheck)
        || (formValue.pwCheck === "")) {
      setErrorMessage(prevState => ({ ...prevState, pwCheckMessage: "비밀번호가 일치하지 않습니다." }));
      setValidationState(prevState => ({ ...prevState, isPwCheck: false }));
    } else {
      setErrorMessage(prevState => ({ ...prevState, pwCheckMessage: "비밀번호가 일치합니다." }));
      setValidationState(prevState => ({ ...prevState, isPwCheck: true }));
    }
  }, [formValue.pw, formValue.pwCheck]);

  const onChangeName = (e) => {
    const currentName = e.target.value;
    setFormValue({ ...formValue, name: currentName});
    const nameRegExp = /^[a-zA-Z|ㄱ-ㅎ가-힣 ]*$/;

    if(!nameRegExp.test(currentName)) {
      setErrorMessage({ ...errorMessage, nameMessage: "한글, 영문만 허용"});
      setValidationState({ ...validationState, isName: false});
    } else {
      setErrorMessage({ ...errorMessage, nameMessage: ""});
      setValidationState({ ...validationState, isName: true});
    }
  };

  const onChangePhone1 = (e) => {
    const currentPhone = e.target.value;
    setFormValue({ ...formValue, phone1: currentPhone});
    const phoneRegExp = /^[0-9]{2,3}$/;

    if(!phoneRegExp.test(currentPhone)) {
      setErrorMessage({ ...errorMessage, phoneMessage: "올바르지 않은 양식"});
      setValidationState({ ...validationState, isPhone1: false});
    } else {
      setErrorMessage({ ...errorMessage, phoneMessage: ""});
      setValidationState({ ...validationState, isPhone1: true});
    }
  };

  const onChangePhone2 = (e) => {
    const currentPhone = e.target.value;
    setFormValue({ ...formValue, phone2: currentPhone});
    const phoneRegExp = /^[0-9]{3,4}$/;

    if(!phoneRegExp.test(currentPhone)) {
      setErrorMessage({ ...errorMessage, phoneMessage: "올바르지 않은 양식"});
      setValidationState({ ...validationState, isPhone2: false});
    } else {
      setErrorMessage({ ...errorMessage, phoneMessage: ""});
      setValidationState({ ...validationState, isPhone2: true});
    }
  };

  const onChangePhone3 = (e) => {
    const currentPhone = e.target.value;
    setFormValue({ ...formValue, phone3: currentPhone});
    const phoneRegExp = /^[0-9]{3,4}$/;

    if(!phoneRegExp.test(currentPhone)) {
      setErrorMessage({ ...errorMessage, phoneMessage: "올바르지 않은 양식"});
      setValidationState({ ...validationState, isPhone3: false});
    } else {
      setErrorMessage({ ...errorMessage, phoneMessage: ""});
      setValidationState({ ...validationState, isPhone3: true});
    }
  };

  const handleAddressChange = (address) => {
    setFormValue({ ...formValue, address: address});
  };

  const handleEmailSelect = (e) => {
    setFormValue({ ...formValue, selected: e.target.value});
  };


  // 회원가입 유효성 검사
  useEffect(() => {
    const validationStatesForKakao = ["isName", "isPhone1", "isPhone2", "isPhone3"];
    const formValuesForKakao = ["name", "nickname", "address", "phone1" ,"phone2", "phone3", "email", "selected"];

    let formValuesFilled;
    let validationStatesTrue;

    if(userId === "") {
      formValuesFilled = Object.values(formValue).every(value => value !== "");
      validationStatesTrue = Object.values(validationState).every(value => value === true);
    } else {
      formValuesFilled = formValuesForKakao.every((field) => formValue[field] !== "");
      validationStatesTrue = validationStatesForKakao.every((field) => validationState[field] === true);
    }

    if(formValuesFilled && validationStatesTrue) {
      setNotAllow(false);
      return;
    }
    setNotAllow(true);
  }, [formValue, validationState]);

  const handleCheckId = async () => {
    try {
      const response = await axiosInstance.get("/jwt-login/check-id", {
        params: {userId: formValue.id}
      });
      const {success, message} = response.data;

      setErrorMessage({ ...errorMessage, idCheckMessage: message});
      setValidationState({ ...validationState, isIdChecked: success});

      onAlert(message);
    } catch (error) {
      console.error("중복 확인 요청 실패: ", error);
      setErrorMessage({ ...errorMessage, idCheckMessage: "서버와 통신 오류"});
    }
  };

  const handleCheckNickname = async () => {
    try {
      const response = await axiosInstance.get("/jwt-login/check-nickname", {
        params: {nickname: formValue.nickname}
      });

      const {success, message} = response.data;

      setErrorMessage({...errorMessage, nicknameCheckMessage: message});
      setValidationState({...validationState, isNicknameChecked: success});

      onAlert(message);
    } catch(error) {
      console.error("중복 확인 요청 실패: ", error);
      setErrorMessage({...errorMessage, idCheckMessage: "서버와 통신 오류"});
    }
  };

  const handleSignUp = async () => {
    const signUpData = {
      userId: formValue.id,
      password: formValue.pw,
      passwordCheck: formValue.pwCheck,
      username: formValue.name,
      address: formValue.address,
      phone: `${formValue.phone1}-${formValue.phone2}-${formValue.phone3}`,
      email: `${formValue.email}@${formValue.selected}`,
      nickname: formValue.nickname,
      kakaoUser: userId,
    };

    console.log(signUpData);

    try {
      const response = await axiosInstance.post("/jwt-login/join", signUpData, {
        headers: {
          Authorization: `Bearer ${token}`, // 토큰을 헤더로 전송
        },
      });
      if (response.data === "회원가입 성공") {
        window.location.href = "/jwt-login/login";
      } else {
        console.error("회원가입 실패:", response.data);
      }
    } catch (error) {
      console.error("회원가입 실패:", error);
    }
  };

  return (
      <div className="signupPage">
        <div className="titleWrap">회원가입</div>
        <div className="signup_contentWrap">
          {!userId && (
              <>
                <div className="signup_inputTitle">아이디
                  <div className="errorMessage">{errorMessage.idMessage}</div>
                </div>
                <div className="signup_inputWrap">
                  <input type="text" className="input" value={formValue.id} onChange={onChangeId} />
                  <button className="checkButton" disabled={!validationState.isId}
                          onClick={() => {handleCheckId();}} >중복 확인</button>
                </div>

                <div className="signup_inputTitle">비밀번호
                  <div className="errorMessage">{errorMessage.pwMessage}</div>
                </div>
                <div className="signup_inputWrap">
                  <input type="password" className="input" value={formValue.pw}
                         onChange={onChangePw} />
                </div>

                <div className="signup_inputTitle">비밀번호 확인
                  <div className="errorMessage"
                       style={{color: validationState.isPwCheck ? '#53b463' : 'red'}}>
                    {errorMessage.pwCheckMessage}</div>
                </div>
                <div className="signup_inputWrap">
                  <input type="password" className="input" value={formValue.pwCheck} onChange={onChangePwCheck} />
                </div>
              </>
          )}

          <div className="signup_inputTitle">이름
            <div className="errorMessage">{errorMessage.nameMessage}</div>
          </div>
          <div className="signup_inputWrap">
            <input type="text" className="input" value={formValue.name} onChange={onChangeName} />
          </div>

          <div className="signup_inputTitle">닉네임</div>
          <div className="signup_inputWrap">
            <input type="text" className="input" value={formValue.nickname} onChange={(e) => setFormValue({ ...formValue, nickname: e.target.value})} />
            <button className="checkButton" onClick={() => {handleCheckNickname();}}>중복 확인</button>
          </div>

          <div className="signup_inputTitle">주소 검색</div>
          <div className="addressWrap">
            <div className="signup_addressInputWrap">
              <input type="text" className="input" readOnly value={formValue.address} />
            </div>
            <Postcode onAddressChange={handleAddressChange} />
          </div>

          <div className="signup_inputTitle">전화번호
            <div className="errorMessage">{errorMessage.phoneMessage}</div>
          </div>
          <div>
            <div className="signup_phoneInputWrap">
              <input type="text" className="input" maxLength={3}
                     onChange={onChangePhone1} />
            </div>
            &nbsp;-&nbsp;
            <div className="signup_phoneInputWrap">
              <input type="text" className="input" maxLength={4}
                     onChange={onChangePhone2} />
            </div>
            &nbsp;-&nbsp;
            <div className="signup_phoneInputWrap">
              <input type="text" className="input" maxLength={4}
                     onChange={onChangePhone3} />
            </div>
          </div>

          <div className="signup_inputTitle">이메일</div>
          <div className="signup_emailWrap">
            <div className="signup_emailInputWrap">
              <input type="text" className="input" value={formValue.email} onChange={(e) => setFormValue({ ...formValue, email: e.target.value})} />
            </div> &nbsp;@&nbsp;
            <div className="signup_emailList">
              <select className="selectEmailWrap" onChange={handleEmailSelect} value={formValue.selected}>
                {emailList.map((item) => (
                    <option value={item} key={item}>
                      {item}
                    </option>
                ))}
              </select>
            </div>
          </div>
        </div>
        <div>
          <button disabled={notAllow} className="joinButton" onClick={handleSignUp}>가입하기</button>
        </div>
      </div>
  );
}