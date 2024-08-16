import React from 'react'
import styled from 'styled-components';
import '../../css/MyPage.css';
import Header from "../../components/my/Header";
import Sidebar from "../../components/my/Sidebar";

export default function MyComment() {
    return (
        <>
            <Header/>
            <div className="side-wrap">
                <Sidebar/>
            </div>
            <Mypost>
                <Title>댓글 단 글</Title>
            </Mypost>
        </>
    );
}

const Mypost = styled.div`
    margin-left: 350px;
    margin-right: 50px;
    width: 60%;
    justify-content: space-around;
`;

const Title = styled.div`
    font-size: 20px;
`;