package com.alugarei.cielo.domain

import scala.xml.Elem

/**
 * Created by Gustavo on 02/02/2016.
 */
trait XmlSerializable {

  def toXml: Elem

}
