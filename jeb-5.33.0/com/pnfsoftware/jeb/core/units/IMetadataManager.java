package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IMetadataManager {
   List getGroups();

   int getGroupCount();

   IMetadataGroup getGroupByName(String var1);

   boolean addGroup(IMetadataGroup var1);

   boolean insertGroup(int var1, IMetadataGroup var2);

   boolean removeGroup(int var1);

   boolean removeGroupByName(String var1);
}
