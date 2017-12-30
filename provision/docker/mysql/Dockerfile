FROM mysql:5.7

RUN \
    echo "Asia/Tokyo" | tee /etc/timezone \
    && dpkg-reconfigure --frontend noninteractive tzdata

COPY my.cnf /etc/mysql/my.cnf