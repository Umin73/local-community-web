import React from 'react'
import styled from 'styled-components';
import Header from "../../components/my/Header";
import Sidebar from "../../components/my/Sidebar";

export default function Bookmark() {
    return (
        <>
            <div className="root-wrap">
                <Header/>
            </div>
            <div className="side-wrap">
                <Sidebar/>
            </div>
            <Mypost>
                <Title>북마크</Title>
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