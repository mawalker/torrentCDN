exec { "apt-get update":
  path => "/usr/bin",
}

package { "nano":
  ensure  => present,
  require => Exec["apt-get update"],
}

package { "zip":
  ensure  => present,
  require => Exec["apt-get update"],
}

package { "curl":
  ensure  => present,
  require => Exec["apt-get update"],
}

package { "openjdk-7-jre-headless":
  ensure  => present,
  require => Exec["apt-get update"],
}
