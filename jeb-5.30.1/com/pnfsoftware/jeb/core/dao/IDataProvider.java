package com.pnfsoftware.jeb.core.dao;

import com.pnfsoftware.jeb.core.properties.IConfiguration;

public interface IDataProvider {
   IUserDatabase getUserDatabase();

   IApplicationDatabase getApplicationDatabase();

   IFileStore getFileStore();

   IFileStore getPluginStore();

   IFileDatabase getProjectDatabase();

   IConfiguration getConfiguration();
}
