import React from "react";
import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Posts from "./routes/posts";
import Write from "./routes/write";
import Post from "./routes/post";
import Edit from "./routes/edit";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/posts" element={<Posts />} />
          <Route path="/post/create" element={<Write />} />
          <Route path="/post/:postId" element={<Post />} />
          <Route path="/post/:postId/edit" element={<Edit />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
