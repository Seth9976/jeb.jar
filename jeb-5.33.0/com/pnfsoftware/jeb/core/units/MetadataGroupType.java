package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum MetadataGroupType {
   OPAQUE,
   STRING,
   CLASSID,
   RGB;
}
