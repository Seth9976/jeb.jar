package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;
import java.util.Map;

@Ser
public interface IMetadataGroup {
   String getName();

   MetadataGroupType getType();

   Map getAllData();

   Object getData(String var1);

   Object getData(String var1, AddressConversionPrecision var2);

   boolean setData(String var1, Object var2);

   List getSectionAnchorIds();
}
