package com.pnfsoftware.jeb.core.dao.impl;

import com.pnfsoftware.jeb.core.dao.IApplicationDatabase;
import com.pnfsoftware.jeb.core.dao.IDataProvider;
import com.pnfsoftware.jeb.core.dao.IFileDatabase;
import com.pnfsoftware.jeb.core.dao.IFileStore;
import com.pnfsoftware.jeb.core.dao.IUserDatabase;
import com.pnfsoftware.jeb.core.properties.IConfiguration;

public abstract class DataProviderAdapter implements IDataProvider {
   protected IUserDatabase userdb;
   protected IApplicationDatabase appdb;
   protected IFileStore filestore;
   protected IFileStore pluginstore;
   protected IFileDatabase projectdb;
   protected IConfiguration config;

   @Override
   public IUserDatabase getUserDatabase() {
      return this.userdb;
   }

   @Override
   public IApplicationDatabase getApplicationDatabase() {
      return this.appdb;
   }

   @Override
   public IFileStore getFileStore() {
      return this.filestore;
   }

   @Override
   public IFileStore getPluginStore() {
      return this.pluginstore;
   }

   @Override
   public IFileDatabase getProjectDatabase() {
      return this.projectdb;
   }

   @Override
   public IConfiguration getConfiguration() {
      return this.config;
   }
}
