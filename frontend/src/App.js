import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import './App.css';

function Home() {
  return (
    <div className="home">
      <h2>Welcome to Library Management System</h2>
      <p>Manage your books, users, and borrowing records efficiently.</p>
      <div className="features">
        <div className="feature-card">
          <h3>📚 Books</h3>
          <p>Manage book inventory</p>
        </div>
        <div className="feature-card">
          <h3>👥 Users</h3>
          <p>Manage library members</p>
        </div>
        <div className="feature-card">
          <h3>📋 Borrowing</h3>
          <p>Track borrowing records</p>
        </div>
      </div>
    </div>
  );
}

function App() {
  return (
    <Router>
      <div className="App">
        <header className="App-header">
          <h1>📚 Library Management System</h1>
          <nav>
            <Link to="/">Home</Link>
          </nav>
        </header>
        <main className="App-main">
          <Routes>
            <Route path="/" element={<Home />} />
          </Routes>
        </main>
        <footer className="App-footer">
          <p>&copy; 2024 Library Management System. All rights reserved.</p>
        </footer>
      </div>
    </Router>
  );
}

export default App;
