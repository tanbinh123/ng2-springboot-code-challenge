var gulp = require('gulp'),
    del = require('del'),
    sequence = require('run-sequence'),
    libPath = 'public/lib',
    appPath = 'public/app',
    nodeModulesPath = 'node_modules';

gulp.task('clean:libs', function () {
  return del(libPath + '/**/*', { force: true });
});

gulp.task('clean:js', function () {
    return del([appPath + '/*.js', appPath + '/*.js.map', '!' + appPath + '/systemjs.config.js'], { force: true });
});

gulp.task('clean', function (done) {
    sequence('clean:libs', 'clean:js', done);
});

gulp.task('copy:libs', function (done) {
    sequence('clean:libs', 'clean:js', 'copy:vendor', 'copy:rxjs', 'copy:angular', 'copy:bootstrap', done);
});

gulp.task('copy:vendor', function() {
  return gulp
    .src([
      nodeModulesPath + '/core-js/client/**/*',
      nodeModulesPath + '/zone.js/dist/zone.js',
      nodeModulesPath + '/reflect-metadata/**/*',
      nodeModulesPath + '/systemjs/dist/system-polyfills.js',
      nodeModulesPath + '/systemjs/dist/system.src.js'])
    .pipe(gulp.dest(libPath));
});

gulp.task('copy:rxjs', function() {
  return gulp
    .src([nodeModulesPath + '/rxjs/**/*'])
    .pipe(gulp.dest(libPath + '/rxjs'));
});

gulp.task('copy:angular', function() {
    return gulp
       .src([nodeModulesPath + '/@angular/**/*'])
       .pipe(gulp.dest(libPath + '/@angular'));
});

gulp.task('copy:bootstrap', function() {
    return gulp
      .src([nodeModulesPath + '/bootstrap/**/*'])
      .pipe(gulp.dest(libPath + '/bootstrap'));
});

gulp.task('default', ['copy:libs']);