// reduxStore.js
import { createStore, combineReducers } from 'redux';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage'; // You can use localStorage or sessionStorage
import { applyMiddleware } from 'redux';

// Initial state for user and doctor
const initialUserState = {
  id: null,
  username: '',
  name: '',
  email: '',
  token: '',
  loginStatus: null,
};

const initialDoctorState = {
  id: null,
  username: '',
  name: '',
  email: '',
  token: '',
  loginStatus: null,
};

// Reducers for user and doctor
const userReducer = (state = initialUserState, action) => {
  switch (action.type) {
    case 'SET_USER':
      return { ...state, ...action.payload };
    case 'LOGOUT_USER':
      return initialUserState;
    default:
      return state;
  }
};

const doctorReducer = (state = initialDoctorState, action) => {
  switch (action.type) {
    case 'SET_DOCTOR':
      return { ...state, ...action.payload };
    case 'LOGOUT_DOCTOR':
      return initialDoctorState;
    default:
      return state;
  }
};

// Persist configuration
const persistConfig = {
  key: 'root',
  storage,
  whitelist: ['user', 'doctor'], // Only persist user and doctor
};

// Combine reducers
const rootReducer = combineReducers({
  user: userReducer,
  doctor: doctorReducer,
});

// Persisted reducer
const persistedReducer = persistReducer(persistConfig, rootReducer);

// Create store with persisted reducer
const store = createStore(persistedReducer, applyMiddleware());

const persistor = persistStore(store);

export { store, persistor };