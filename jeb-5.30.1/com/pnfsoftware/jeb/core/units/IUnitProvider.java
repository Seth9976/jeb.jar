package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IUnitProvider {
   String getFormatType();

   String getDescription();

   IUnitFormatter getFormatter();
}
