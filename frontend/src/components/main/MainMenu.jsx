import React from "react";
import styled from "styled-components";
import '../../css/Main.css';
import { useNavigate } from 'react-router-dom';

const TopMenuWrapper = styled.div`
    display: flex;
    justify-content: center;
`;

const MenuWrapper = styled.div`
    display: flex;
    justify-content: center;
    background-color: #eeeff0;
`;

function MainMenu(props) {
    const {} = props;

    const navigate = useNavigate();

    const handleClick = (id, categoryName) => {
        console.log("오잉?");
        navigate(`/posts/${id}`, { state: { name: categoryName } });
    };

    return (
        <>
            <TopMenuWrapper>
                <nav id="topMenu">
                    <ul>
                        <li className="topMenuLi"> <a className="menuLink" href="#">About us</a></li>
                        <li>|</li>
                        <li className="topMenuLi"><a className="menuLink" href="#">Comunity</a>
                            <ul className="submenu">
                                <li><a href="#" target="middle" className="submenuLink longLink" onClick={() => handleClick(1, "자유")}>자유 게시판</a></li>
                                <li><a href="#" className="submenuLink longLink" onClick={() => handleClick(2, "정보")}>정보 게시판</a></li>
                                <li><a href="#" className="submenuLink longLink" onClick={() => handleClick(3, "홍보")}>홍보 게시판</a></li>
                            </ul>
                        </li>
                        <li>|</li>
                        <li className="topMenuLi"><a className="menuLink" href="#">Review</a>
                            <ul className="submenu">
                                <li><a href="#" className="submenuLink">식당</a></li>
                                <li><a href="#" className="submenuLink">카페·베이커리</a></li>
                                <li><a href="#" className="submenuLink">의료</a></li>
                                <li><a href="#" className="submenuLink">패션·미용</a></li>
                                <li><a href="#" className="submenuLink">동물</a></li>
                                <li><a href="#" className="submenuLink">교육</a></li>
                                <li><a href="#" className="submenuLink">여가</a></li>
                                <li><a href="#" className="submenuLink">기타</a></li>
                            </ul>
                        </li>
                        <li>|</li>
                        <li className="topMenuLi"><a className="menuLink" href="#">HELP</a>
                            <ul className="submenu">
                                <li><a href="#" className="submenuLink">분실</a></li>
                                <li><a href="#" className="submenuLink">실종</a></li>
                                <li><a href="#" className="submenuLink">사고</a></li>
                            </ul>
                        </li>
                        <li>|</li>
                        <li className="topMenuLi"><a className="menuLink" href="#">FAQ</a></li>
                    </ul>
                </nav>
            </TopMenuWrapper>

            <hr/>

            <MenuWrapper>
                <div>
                    <table className="menu">
                        <tbody>
                            <tr valign="top">
                                <td>
                                    <dl>
                                        <dt><a href="#" target="middle" className="txt" onClick={() => handleClick(1, "자유")}>🆓 자유 게시판</a></dt>
                                        <dt><a href="#" className="txt" onClick={() => handleClick(2, "정보")}> &nbsp; ℹ &nbsp; 정보 게시판</a></dt>
                                        <dt><a href="#" className="txt" onClick={() => handleClick(3, "홍보")}>📢 홍보 게시판</a></dt>
                                    </dl>
                                </td>

                                <td>
                                    <dl>
                                        <dt><a href="#" className="txt">🍴 식당</a></dt>
                                        <dt><a href="#" className="txt">☕ 카페·베이커리</a></dt>
                                        <dt><a href="#" className="txt">🏥 의료</a></dt>
                                        <dt><a href="#" className="txt">💄 패션·미용</a></dt>
                                    </dl>
                                </td>

                                <td>
                                    <dl>
                                        <dt><a href="#" className="txt">🐾 동물</a></dt>
                                        <dt><a href="#" className="txt">🏫 교육</a></dt>
                                        <dt><a href="#" className="txt">🎉 여가</a></dt>
                                        <dt><a href="#" className="txt">✴ 기타</a></dt>
                                    </dl>
                                </td>

                                <td>
                                    <dl>
                                        <dt><a href="#" className="txt">🚩 분실</a></dt>
                                        <dt><a href="#" className="txt">🆘 실종</a></dt>
                                        <dt><a href="#" className="txt">🚨 사고</a></dt>
                                    </dl>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </MenuWrapper>
        </>
    );
}

export default MainMenu;
