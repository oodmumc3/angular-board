
import {IAppConfig} from './app-config';
import {InjectionToken} from '@angular/core';

export const APP_DI_CONFIG: IAppConfig = {
  API_END_POINT: '/api'
};

export let APP_CONFIG = new InjectionToken< IAppConfig >('app.config');
