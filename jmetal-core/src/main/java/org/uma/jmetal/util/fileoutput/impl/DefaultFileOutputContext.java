//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.uma.jmetal.util.fileoutput.impl;

import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.fileoutput.FileOutputContext;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Class using the default method for getting a buffered writer
 *
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
public class DefaultFileOutputContext implements FileOutputContext {
    private static final String DEFAULT_SEPARATOR = " ";

    protected String fileName;
    protected String separator;
    protected boolean isAppend;

    public DefaultFileOutputContext(String fileName) {
        this.fileName = fileName;
        this.separator = DEFAULT_SEPARATOR;
        this.isAppend = false;
    }

    public DefaultFileOutputContext(String fileName, boolean isAppend) {
        this.fileName = fileName;
        this.separator = DEFAULT_SEPARATOR;
        this.isAppend = isAppend;
    }

    @Override
    public BufferedWriter getFileWriter() {
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(fileName, isAppend);
        } catch (FileNotFoundException e) {
            throw new JMetalException("Exception when calling method getFileWriter()", e);
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

        return new BufferedWriter(outputStreamWriter);
    }

    @Override
    public String getSeparator() {
        return separator;
    }

    @Override
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    @Override
    public boolean getIsAppend() {
        return isAppend;
    }

    @Override
    public void setIsAppend(boolean isAppend) {
        this.isAppend = isAppend;
    }
}
