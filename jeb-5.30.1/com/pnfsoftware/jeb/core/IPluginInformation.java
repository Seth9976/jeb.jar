package com.pnfsoftware.jeb.core;

public interface IPluginInformation {
   String getName();

   String getDescription();

   String getAuthor();

   Version getVersion();

   Version getMinimumCoreVersion();

   Version getMaximumCoreVersion();
}
