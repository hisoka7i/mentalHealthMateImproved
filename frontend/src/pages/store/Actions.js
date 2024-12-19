// Action types
export const SET_USER = 'SET_USER';

// Action creators
export const setUser = (userData) => {
  return {
    type: SET_USER,
    payload: userData, // Data to be stored in Redux state
  };
};
